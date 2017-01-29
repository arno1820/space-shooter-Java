using UnityEngine;
using System.Collections.Generic;

public class _GameControler : MonoBehaviour {

    public Camera mainCam;
    public GameObject background;
    public BoxCollider2D leftWall;
    public BoxCollider2D rightWall;
    public BoxCollider2D topWall;
    public BoxCollider2D bottomWall;
    public GameObject enemy1;
    public GameObject player;
    public GameObject Dustlane1;
    public GameObject Dustlane2;
    public KeyCode stop;

    private bool gameOver;
    private SpriteRenderer srBackground;
    private int enemies = 0;
    private int wave = 1;
    // Use this for initialization
    void Start() {

        srBackground = background.GetComponent<SpriteRenderer>();
        srBackground.sprite.texture.filterMode = FilterMode.Point;
        setPlayerCorrectly();
    }

    // Update is called once per frame
    void Update() {


        if (Input.GetKey(stop)) Application.Quit();

        fitCamera(srBackground, background);
        fitCamera(Dustlane1.GetComponent<SpriteRenderer>(), Dustlane1);
        fitCamera(Dustlane2.GetComponent<SpriteRenderer>(), Dustlane2);
        setBoundaries();
        updateWave();
    }

    private void fitCamera(SpriteRenderer sr, GameObject toScale)
    {
        double width = sr.sprite.bounds.size.x;
        double worldScreenHeight = Camera.main.orthographicSize * 2.0;
        double worldScreenWidth = worldScreenHeight / Screen.height * Screen.width;
        double height = sr.sprite.bounds.size.y;

        toScale.transform.localScale = new Vector2((float)(worldScreenWidth / width), (float)(worldScreenHeight / height));

    }

    private void setBoundaries()
    {
        topWall.size = new Vector2(mainCam.ScreenToWorldPoint(new Vector3(Screen.width * 2f, 0f, 0f)).x, 1f);
        topWall.offset = new Vector2(0f, mainCam.ScreenToWorldPoint(new Vector3(0f, Screen.height, 0f)).y + 0.5f);

        bottomWall.size = new Vector2(mainCam.ScreenToWorldPoint(new Vector3(Screen.width * 2f, 0f, 0f)).x, 1f);
        bottomWall.offset = new Vector2(0f, -1 * mainCam.ScreenToWorldPoint(new Vector3(0f, Screen.height, 0f)).y - 0.5f);

        leftWall.size = new Vector2(1f, mainCam.ScreenToWorldPoint(new Vector3(0f, Screen.height * 2f, 0f)).y);
        leftWall.offset = new Vector2(mainCam.ScreenToWorldPoint(new Vector3(0f, Screen.width, 0f)).x - 0.5f, 0f);

        rightWall.size = new Vector2(1f, mainCam.ScreenToWorldPoint(new Vector3(0f, Screen.height * 2f, 0f)).y);
        rightWall.offset = new Vector2(-1 * mainCam.ScreenToWorldPoint(new Vector3(0f, Screen.width, 0f)).x + 0.5f, 0f);

    }

    private void setPlayerCorrectly()
    {
        //top bottem-left point of the screen
        Vector2 min = Camera.main.ViewportToWorldPoint(new Vector2(0, 0));
        //top bottem-right point of the screen
        Vector2 max = Camera.main.ViewportToWorldPoint(new Vector2(1, 1));

        player.transform.position = new Vector2(-max.x * 0.9f, 0);
    }

    private void updateWave()
    {

        //TODO: spawnen alle enemies? location wrong?
        //TODO: Still very basic, needs more!
        if (enemies == 0)
        {
            for (int i = wave; i > 0; i--)
            {
                enemies++;
                spawnEnemy();
            }
            wave += 50;

        }
    }

    private void spawnEnemy()
    {
        //top bottem-left point of the screen
        Vector2 min = Camera.main.ViewportToWorldPoint(new Vector2(0, 0));
        //top bottem-right point of the screen
        Vector2 max = Camera.main.ViewportToWorldPoint(new Vector2(1, 1));
        GameObject enemy = (GameObject)Instantiate(enemy1);
        enemy.transform.position = new Vector2(Random.Range(max.x * 0.9f, max.x), Random.Range(min.y, max.y));
        tinyEnemyScript tis = (tinyEnemyScript) enemy.GetComponent("tinyEnemyScript");
        tis.setGameControler(this);
    }

    public GameObject getPlayer()
    {
        return player;
    }

    public bool getGameOver()
    {
        return gameOver;
    }

    public void enemyDead()
    {
        enemies--;
    }

}
