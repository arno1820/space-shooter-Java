using UnityEngine;
using System.Collections;

public class bulletScript : MonoBehaviour {

    _GameControler gc;
    private Rigidbody2D rB;

	// Use this for initialization
	void Start () {

        rB = GetComponent<Rigidbody2D>();
        rB.velocity = new Vector2(10, 0);
    }
	
	// Update is called once per frame
	void Update () {
	    
        

	}

    void OnTriggerEnter2D(Collider2D col)
    {
        if (col.tag == "Enemy" || col.tag == "Wall")
        {
           Destroy(gameObject);
        }
    }

}
