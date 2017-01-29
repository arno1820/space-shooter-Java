using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Done_BGScroller : MonoBehaviour
{
	public float scrollSpeed;
	private float tileSizeZ;

	private Vector3 startPosition;

    void Start()
    {
        startPosition = transform.position;
        tileSizeZ = (float) (Camera.main.orthographicSize * 2.0 / Screen.height * Screen.width);
    }

	void Update ()
	{
		float newPosition = Mathf.Repeat(Time.time * scrollSpeed, tileSizeZ);
		transform.position = startPosition + new Vector3(-1,0) * newPosition;
	}

    public float getScrollSpeed()
    {
        return scrollSpeed;
    }

}