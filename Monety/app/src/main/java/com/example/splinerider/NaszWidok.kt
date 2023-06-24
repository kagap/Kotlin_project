package com.example.splinerider


import android.content.ContentValues
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class NaszWidok(context: Context, attrs: AttributeSet): View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var kolor = Color.rgb(175,0,181)
    private var zielony = Color.GREEN
    private var czarny = Color.BLACK
    private var multi: Boolean = false
    private var x1: Float = 0.0f
    private var y1: Float = 0.0f
    private var r: Int = 0
    private var g: Int = 0
    private var b: Int = 0
    private lateinit var rand: Random
    private var mActivePointerId: Int = 0
    private var ls: MutableList<PointF> = mutableListOf<PointF>()
    private var image_1: Bitmap? = null
    private var image_2: Bitmap? = null
    private var MSize: Float = 400f

    init {
//         Load the image from a drawable resource
        val res = context.resources
        var drawableId = R.drawable.moneta1 // Replace with your image resource ID
        image_1 = BitmapFactory.decodeResource(res, drawableId)
        drawableId = R.drawable.moneta5
        image_2 = BitmapFactory.decodeResource(res, drawableId)
    }


    fun SetRand(){
        r=rand.nextInt(0,255)
        g=rand.nextInt(0,255)
        b=rand.nextInt(0,255)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val prost = RectF(0f, 0f, MSize, MSize)

        for( p in ls) {

            prost.left = p.x - MSize/2
            prost.top = p.y - MSize/2

            prost.right = p.x + MSize/2
            prost.bottom = p.y + MSize/2



            if (multi) {
                paint.color = Color.rgb(r, g, b)
                paint.style = Paint.Style.FILL

                canvas.drawOval(prost, paint)

                image_2?.let {
                    canvas.drawBitmap(it, null, prost, null)
                }

//                paint.color = czarny
//                paint.strokeWidth = 20f
//                paint.style = Paint.Style.STROKE
//                canvas.drawOval(prost, paint)
            } else {
                paint.color = Color.rgb(r, g, b)
                paint.style = Paint.Style.FILL

//                canvas.drawRect(prost, paint)

                // Draw the image onto the canvas
                image_1?.let {
                    canvas.drawBitmap(it, null, prost, null)
                }

//                paint.color = czarny
//                paint.strokeWidth = 20f
//                paint.style = Paint.Style.STROKE
//                canvas.drawRect(prost, paint)
            }

        }


    }

    //Przykład Multi-touch
    override fun onTouchEvent(event: MotionEvent): Boolean {

        val pc = event.getPointerCount()

        event.getActionMasked().let { action ->
                //Log.d(ContentValues.TAG, "The action is ${MotionEvent.actionToString(action)}")
                // Obsługa akcji
              when(action){
                  MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN-> {
                      rand = Random(System.currentTimeMillis())
                      SetRand()
                      event.getActionIndex().let { index ->
                          val point = PointF(event.getX(index) ,event.getY(index))
                          ls.add(index,point) //Dodawanie położeń do listy
                      }
                  }

                  MotionEvent.ACTION_MOVE -> {
                       for(p in ls){
                           val index =  ls.indexOf(p)
                           val point = PointF(event.getX(index) ,event.getY(index))
                           ls.set(index,point) // Ustawianie położeń na liście
                       }
                  }

                  MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP,MotionEvent.ACTION_CANCEL ->{
                      event.getActionIndex().let { index ->
                          ls.removeAt(index) // Usuwanie położeń z listy
                          SetRand()
                      }

                  }
                  else -> {}
              }

        }


        Log.d(ContentValues.TAG, "Touch: "+pc+" Size: "+ls.size)

        multi = event.pointerCount>1

        invalidate()
        return true
    }

}