using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class backgroundScript : MonoBehaviour {

    public Sprite one;
    public Sprite two;
    public Sprite three;
    public float scrollSpeed;

    private SpriteRenderer sr;
    private float tileSizeZ;
    private GameObject parent;
    private Vector3 startPosition;

    // Use this for initialization
    void Start () {

        startPosition = transform.position;
        tileSizeZ = (float)(Camera.main.orthographicSize * 2.0 / Screen.height * Screen.width);

        sr = gameObject.GetComponent<SpriteRenderer>();
        try
        {
            parent = transform.parent.gameObject;
        }
        catch
        {
            parent = null;
        }
    }
	
	// Update is called once per frame
	void Update () {
        
        if (parent == null)
        {
            if (transform.position.x <= -tileSizeZ)
            {
                transform.position = new Vector2(tileSizeZ, 0);
                transform.GetChild(0).localPosition = transform.GetChild(0).localPosition * -1;
                if (sr.sprite == one) sr.sprite = two;
                else if (sr.sprite == two && !(three == null)) sr.sprite = three;
                else sr.sprite = one;
            }
            else
            {
                transform.position = new Vector2(transform.position.x - Time.deltaTime * scrollSpeed, 0);
            }
            

        }

        Vector3 watch = transform.localPosition;
        
        if (transform.position.x + (tileSizeZ/2) <= -tileSizeZ/2 + 2*Time.deltaTime*scrollSpeed && parent != null)
        {
            transform.localPosition = transform.localPosition * -1;
            changeSprite();
        }

        
                
        }
    private void changeSprite()
    {
        if (sr.sprite == one) sr.sprite = two;
        else if (sr.sprite == two && !(three == null)) sr.sprite = three;
        else sr.sprite = one;
    }
}

