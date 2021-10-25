package ru.alinadorozhkina.yadictionaryapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alinadorozhkina.yadictionaryapp.databinding.ItemWordBinding
import ru.alinadorozhkina.yadictionaryapp.model.DataModel
import ru.alinadorozhkina.yadictionaryapp.model.Def

class MainAdapter(
    private var data: DataModel
): RecyclerView.Adapter<MainAdapter.ViewHolder>(){

        fun setData(data: DataModel) {
        this.data = data
        notifyDataSetChanged()
    }


    inner class ViewHolder(val vb: ItemWordBinding): RecyclerView.ViewHolder(vb.root){

        fun bindDef(data: Def){
           vb.tvWord.text=data.text
            Log.d("Adapter bind data: Meaning data.text", data.text)
            vb.tvTranscription.text=data.ts
            Log.d("Adapter bind data: Meaning data ts", data.ts)
            vb.tvTranslation.text = data.tr.firstOrNull()?.text
            vb.tvExample.text=data.tr.firstOrNull()?.example?.firstOrNull()?.text
            vb.tvExampleTranslation.text = data.tr.firstOrNull()?.example?.firstOrNull()?.translation?.firstOrNull()?.translation

        }

        fun bindMain(dataModel: Def) {
            Log.d("Adapter bindMain DataModel1", dataModel.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDef(data.def[position])
        Log.d("onBindViewHolder", data.def[position].tr[position].toString())
        Log.d("size", data.def.size.toString())
        Log.d("position", position.toString())
        Log.d("last index", data.def.lastIndex.toString())

    }

    override fun getItemCount(): Int = data.def.size-1
}
