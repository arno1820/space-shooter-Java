using UnityEngine;
using System.Collections;

public class playerScript : MonoBehaviour {

    public _GameControler gc;

    public KeyCode up;
    public KeyCode down;
    public KeyCode forward;
    public KeyCode backward;
    public KeyCode shoot;

    public GameObject bulletPrefab;
    public GameObject bulletSilo1;
    public GameObject bulletSilo2;

    // {+X, -X, +Y, -Y}
    private float[] speed = { 5, -2, 3, -3 };
    private Rigidbody2D rB;
    private float cooldown = 0;
    private Animator anim;
    private bool alternate = true;
    private int hp = 100;

    // Use this for initialization
    void Start() {

        transform.position = new Vector3(-1 * gc.mainCam.ScreenToWorldPoint(new Vector3(Screen.width, 0f, 0f)).x
           + transform.localScale.x + 3, 0f, 0f);
        rB = GetComponent<Rigidbody2D>();
        anim = gameObject.GetComponent<Animator>();

    }

    // Update is called once per frame
    void Update()
    {
        float X = gameObject.transform.position.x;
        float dt = Time.deltaTime;
        float Y = gameObject.transform.position.y;
        rB.drag = 0f;
        bool moved = false;

        if (Input.GetKey(up))
        {
            moved = true;
            rB.velocity = new Vector2(rB.velocity.x, speed[2]);
            anim.SetInteger("movement", 2);
        }
        else { rB.velocity = new Vector2(rB.velocity.x, 0); }

        if (Input.GetKey(down))
        {
            moved = true;
            rB.velocity = new Vector2(rB.velocity.x, speed[3]);
            anim.SetInteger("movement", 1);
        }


        if (Input.GetKey(forward))
        {
            moved = true;
            rB.velocity = new Vector2(speed[0], rB.velocity.y);
            anim.SetInteger("movement", 3);
        }
        else { rB.velocity = new Vector2(0, rB.velocity.y); }

        if (Input.GetKey(backward))
        {
            moved = true;
            rB.velocity = new Vector2(speed[1], rB.velocity.y);
            anim.SetInteger("movement", 0);
        }

        if (Input.GetKey(shoot) && cooldown <= 0)
        {
            cooldown = 5;
            GameObject bullet = (GameObject)Instantiate(bulletPrefab);
            
            if (alternate)
            {
                bullet.transform.position = bulletSilo1.transform.position;
                alternate = false;
            }
            else
            {
                alternate = true;
                bullet.transform.position = bulletSilo2.transform.position;
            }
        } else if (cooldown > 0) { cooldown--; }
        if (!moved)
        {
            rB.velocity = new Vector2(0f, 0f);
            anim.SetInteger("movement", 0);
        }
    }

    private void hit(int minhp)
    {
        hp -= minhp;
        if (hp < 0) Destroy(gameObject);
    }

    void OnTriggerEnter2D(Collider2D col)
    {
        if (col.tag == "Enemy")
        {
            hit(10);
        }
        if (col.tag == "Wall") rB.velocity = new Vector2(0f, 0f);
    }

}
