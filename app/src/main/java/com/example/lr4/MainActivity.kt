package com.example.lr4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var playerAdapter: PlayerAdapter
    private val players = mutableListOf<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addPlayerButton: Button = findViewById(R.id.addPlayerButton)
        addPlayerButton.setOnClickListener {
            addPlayerButtonClicked()
        }

        val formTeamButton: Button = findViewById(R.id.formTeamButton)
        formTeamButton.setOnClickListener {
            formTeams()
        }

        val lotteryButton: Button = findViewById(R.id.lotteryButton)
        lotteryButton.setOnClickListener {
            performLottery()
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val playerRecyclerView: RecyclerView = findViewById(R.id.playerRecyclerView)
        playerAdapter = PlayerAdapter(players) { player ->
            player.isChecked = !player.isChecked
        }
        playerRecyclerView.adapter = playerAdapter
        playerRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun addPlayerButtonClicked() {
        val playerNameTextView: EditText = findViewById(R.id.playerNameTextView)
        val playerName = playerNameTextView.text.toString()
        if (playerName.isNotBlank()) {
            val player = Player(playerName)
            players.add(player)
            playerAdapter.notifyDataSetChanged()
            playerNameTextView.text.clear()
        }
    }

    private fun formTeams() {
        val redTeamPlayers = players.filter { it.isChecked }
        val greenTeamPlayers = players.filter { !it.isChecked }

        redTeamPlayers.forEach { player ->
            player.teamName = "команда красных"
        }

        greenTeamPlayers.forEach { player ->
            player.teamName = "команда зеленых"
        }

        playerAdapter.notifyDataSetChanged()
    }



    private fun setTeamName(team: List<Player>, teamName: String) {
        team.forEach { player ->
            player.teamName = teamName
        }
    }


    private fun performLottery() {
        val redTeamPlayers = players.filter { it.teamName == "команда красных" }
        val greenTeamPlayers = players.filter { it.teamName == "команда зеленых" }

        val winner = when {
            redTeamPlayers.size > greenTeamPlayers.size -> "команда красных"
            greenTeamPlayers.size > redTeamPlayers.size -> "команда зеленых"
            else -> "ничья"
        }

        Toast.makeText(this, "Победила $winner", Toast.LENGTH_SHORT).show()
    }
}