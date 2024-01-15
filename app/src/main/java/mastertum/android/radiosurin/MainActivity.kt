package mastertum.android.radiosurin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource



open class MainActivity : AppCompatActivity() {

    private lateinit var Player1: ExoPlayer
    private lateinit var Player2: ExoPlayer

    private lateinit var card1 : CardView
    private lateinit var card2 : CardView
    private lateinit var card3 : CardView
    private lateinit var card4 : CardView


    companion object {
        const val URL1 = "http://radio-org-02-ott.prd.go.th:8370"  // FM93.50 MHz
        const val URL2 = "http://radio-org-02-ott.prd.go.th:8380"  // FM97.50 MHz

   
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareMediaPlayer1()
        prepareMediaPlayer2()
        initListeners()



    }



    private fun prepareMediaPlayer1() {

        val mediaDataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(this)

        val mediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(MediaItem.fromUri(URL1))

        val mediaSourceFactory = DefaultMediaSourceFactory(mediaDataSourceFactory)

        Player1 = ExoPlayer.Builder(this)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()

        Player1.addMediaSource(mediaSource)
        Player1.prepare()

    }

    private fun prepareMediaPlayer2() {

        val mediaDataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(this)

        val mediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(MediaItem.fromUri(URL2))

        val mediaSourceFactory = DefaultMediaSourceFactory(mediaDataSourceFactory)

        Player2 = ExoPlayer.Builder(this)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()

        Player2.addMediaSource(mediaSource)
        Player2.prepare()

    }


    private fun initListeners() {
        card1 = findViewById(R.id.card1)
        card1.setOnClickListener {
            Toast.makeText(this, "คุณกำลังฟัง FM. 93.50 MHz.", Toast.LENGTH_SHORT).show()
            pauseAndResumePlayer()
            Player2.playWhenReady = false
            Player1.playWhenReady = true

        }

        card2 = findViewById(R.id.card2)
        card2.setOnClickListener {
            Toast.makeText(this, "คุณกำลังฟัง FM. 97.50 MHz.", Toast.LENGTH_SHORT).show()
            pauseAndResumePlayer()
            Player1.playWhenReady = false
            Player2.playWhenReady = true

        }

        card3 = findViewById(R.id.card3)
        card3.setOnClickListener {
            pauseAndResumePlayer()
            Player1.playWhenReady = false
            Player2.playWhenReady = false

        }

        card4 = findViewById(R.id.card4)
        card4.setOnClickListener {
            pauseAndResumePlayer()
            Player1.playWhenReady = false
            Player2.playWhenReady = false
            openActivity_contact()

        }

    }

    private fun pauseAndResumePlayer() {
        val currentPosition1 = Player1.currentPosition ?: 0
        Player1.seekTo(currentPosition1)
        val currentPosition2 = Player2.currentPosition ?: 0
        Player2.seekTo(currentPosition2)
    }

    private fun openActivity_contact() {
        val intent = Intent(this, Activity_contact::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        pauseAndResumePlayer()
        Player1.playWhenReady = false
        Player2.playWhenReady = false
        super.onDestroy()
    }

}

