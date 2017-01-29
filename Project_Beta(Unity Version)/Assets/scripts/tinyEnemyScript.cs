using UnityEngine;
using System.Collections;

public class tinyEnemyScript : MonoBehaviour {

    private Rigidbody2D rB;
    private int cooldown = 75;
    // array, showing attack state: [state, prevX]
    private int[] attackArray = { 0, 0 };
    public behaviour behaviourThis = behaviour.IDLE;
    private float[] speed = { 2, -5, 3, -3 };
    private _GameControler level;
    public float[] destination = new float[2];

    // Use this for initialization
    void Start () {
        rB = GetComponent<Rigidbody2D>();
    }

    public void setGameControler(_GameControler gc)
    {
        level = gc;
    }

    // Update is called once per frame
    void Update () {

        if (behaviourThis == behaviour.IDLE)
        {

            if (cooldown <= 0)
            {
                cooldown = 20;

                int next = Random.Range(0, 10);
                if (next < 7) {
                    //top bottem-left point of the screen
                    Vector2 min = Camera.main.ViewportToWorldPoint(new Vector2(0, 0));
                    //top bottem-right point of the screen
                    Vector2 max = Camera.main.ViewportToWorldPoint(new Vector2(1, 1));
                    destination[0] = Random.Range(-max.x * 0.7f, max.x);
                    destination[1] = Random.Range(min.y, max.y); 
                    behaviourThis = behaviour.MOVE;
                }
                if (next >= 7 && !level.getGameOver()) behaviourThis = behaviour.SHOOT;
            }
            cooldown--;

        }


        if (behaviourThis == behaviour.SHOOT)
        {
             //make ready for attack
            //[0,x]
            if (attackArray[0] == 0)
            {
                if (level.getPlayer() == null)
                {
                    behaviourThis = behaviour.IDLE;
                    return;
                }
                destination[0] = level.getPlayer().transform.position.x;
                destination[1] = transform.position.y;
                attackArray[1] = (int)transform.position.x;
                attackArray[0] = 1;
                speed[1] = -10;
            }

            //Attack
            //[1,XPOS]
            if (attackArray[0] == 1)
            {
                this.moveToLocation();

                //backing off (if nothing hit)
                if (arrived())
                {
                    destination[0] = attackArray[1];
                    attackArray[0] = 0;
                    behaviourThis = behaviour.MOVE;
                    speed[1] = -5;
                }
                
            }

        }

        if (behaviourThis == behaviour.MOVE)
        {

            if (arrived())
            {
                behaviourThis = behaviour.IDLE;
            }
            else
            {
                //bool isBlocked = 
                moveToLocation();
                //if (isBlocked) behaviourThis = behaviour.IDLE;
            }
        }

    }

    void OnTriggerEnter2D(Collider2D col)
    {
        if(col.tag == "Player" || col.tag == "Bullet")
        {
            Destroy(gameObject);
            level.enemyDead();
        }
    }

    private void moveToLocation()
    {

        float XPos = transform.position.x;
        float YPos = transform.position.y;

        if (destination[1] > YPos)
        {
            rB.velocity = new Vector2(rB.velocity.x, speed[2]);
        }
        else { rB.velocity = new Vector2(rB.velocity.x, 0); }

        if (destination[1] < YPos)
        {
            rB.velocity = new Vector2(rB.velocity.x, speed[3]);
        }
        if (destination[0] > XPos)
        {
            rB.velocity = new Vector2(speed[0], rB.velocity.y);
        }
        else { rB.velocity = new Vector2(0, rB.velocity.y); }

        if (destination[0] < XPos)
        {
            rB.velocity = new Vector2(speed[1], rB.velocity.y);
        }

        //if (XPos == transform.position.x && YPos == transform.position.y)
        //{
           //rB.velocity = new Vector2(0f, 0f);
           //return true;
        //}
        //return false;
        }

    private bool arrived()
    {
        if (transform.position.x > destination[0] - 0.2 && transform.position.x < destination[0] + 0.2)
        {
            destination[0] = transform.position.x;
        }
        if (transform.position.y > destination[1] - 0.2 && transform.position.y < destination[1] + 0.2)
        {
            destination[1] = transform.position.y;
        }
        if (transform.position.x == destination[0] && transform.position.y == destination[1])
        {
            return true;
        }
        else { return false; }
    }

}
