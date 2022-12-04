package com.akash.coroutinelearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*


/**
 * the concept of coroutines has been around since the dawn of programming languages.
 * The first language to explore using coroutines was Simula in 1967.
 *
 * Coroutines build upon regular functions by adding two new operations.
 * In addition to invoke (or call) and return, coroutines add suspend and resume.
 *
 *  suspend — pause the execution of the current coroutine, saving all local variables
 *
 *  resume — continue a suspended coroutine from the place it was paused
 *
 *  Whenever a coroutine is suspended, the current stack frame
 *  (the place that Kotlin uses to keep track of which function is running and its variables)
 *  is copied and saved for later. When it resumes, the stack frame is copied back from where
 *  it was saved and starts running again. In the middle of the animation — when all of
 *  the coroutines on the main thread are suspended, the main thread is free to update
 *  the screen and handle user events. Together, suspend and resume replace callbacks. Pretty neat!
 *
 *  However, coroutines by themselves don’t help you keep track of the work that’s being done.
 *  It’s perfectly fine to have a large number of coroutines — hundreds or
 *  even thousands — and have all of them suspended at the same time. And, while coroutines are cheap,
 *  the work they perform is often expensive, like reading files or making network requests.
 *
 *  A work leak is like a memory leak, but worse. It’s a coroutine that’s been lost.
 *  In addition to using memory, a work leak can resume itself to use CPU, disk,
 *  or even launch a network request.
 */
class MainActivity : AppCompatActivity() {


    val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        launchACoroutine()






    }



    fun launchWithLifecycleScope(){
        lifecycleScope.launch {
            Log.d("coroutine", "lifecycleScope: started " + Thread.currentThread().name)
            launchCoroutineWithContext()
            Log.d("coroutine", "lifecycleScope: ended " + Thread.currentThread().name)

        }
    }




    fun launchACoroutine(){
        Log.d("coroutine", "launchACoroutine: started 2 " + Thread.currentThread().name)
        CoroutineScope(Dispatchers.Default).launch{
            Log.d("coroutine", "launchACoroutine: started 2 " + Thread.currentThread().name)
            longRunningTask()
            Log.d("coroutine", "launchACoroutine: ended 2 " + Thread.currentThread().name)
        }
        Log.d("coroutine", "launchACoroutine: ended 2 " + Thread.currentThread().name)
    }


    suspend fun launchCoroutineWithContext(){
        withContext(Dispatchers.Default){
            longRunningTask()
        }
    }


    suspend fun longRunningTask(){
        Log.d("coroutine", "longRunningTask: started " + Thread.currentThread().name)
        delay(5000)
        Log.d("coroutine", "longRunningTask: ended " + Thread.currentThread().name)
    }





}