package com.example.lr4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter(private val players: List<Player>, private val onPlayerChecked: (Player) -> Unit) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)

        val teamNameTextView: TextView = holder.itemView.findViewById(R.id.teamNameTextView)
        teamNameTextView.text = player.teamName
    }

    override fun getItemCount(): Int {
        return players.size
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerNameTextView: TextView = itemView.findViewById(R.id.playerNameTextView)
        private val teamNameTextView: TextView = itemView.findViewById(R.id.teamNameTextView)
        private val playerCheckBox: CheckBox = itemView.findViewById(R.id.playerCheckBox)

        fun bind(player: Player) {
            playerNameTextView.text = player.name
            teamNameTextView.text = player.teamName
            playerCheckBox.isChecked = player.isChecked

            playerCheckBox.setOnCheckedChangeListener { _, isChecked ->
                player.isChecked = isChecked
                onPlayerChecked.invoke(player)
            }
        }
    }
}
