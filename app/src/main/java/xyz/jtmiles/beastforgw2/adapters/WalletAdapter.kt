package xyz.jtmiles.beastforgw2.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.managers.StorageManager
import xyz.jtmiles.beastforgw2.models.Currency
import xyz.jtmiles.beastforgw2.models.Wallet
import xyz.jtmiles.beastforgw2.services.CurrencyService
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView
import java.util.*


class WalletAdapter(private val mContext: Context, private val mWallet: List<Wallet>) : RecyclerView.Adapter<WalletAdapter.ViewHolder>() {
    private val mCurrencyService: CurrencyService

    init {
        mCurrencyService = Utils.getRetrofit(true).create(CurrencyService::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.wallet_list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wallet = mWallet[position]

        val storageManager = StorageManager(mContext)
        val currencyList = storageManager.loadCurrencyList()

        val currency = currencyList.currencyList.filter { it.id == wallet.id }.firstOrNull()

        if (currency != null && (Date().time - currencyList.lastUpdated.time) < 15*60*1000) {

            Log.d("WalletAdapter", "Loading from cache")

            holder.tvCurrencyName.text = currency.name
            if (currency.name == "Coin")
            {
                var value = wallet.value
                val gold = value/10000
                value %= 10000
                val silver = value/100
                val copper = value%100
                holder.tvCurrencyAmount.text = "${gold}g ${silver}s ${copper}c"
            }
            else
                holder.tvCurrencyAmount.text = wallet.value.toString()

            Glide.with(mContext).load(currency.icon).into(holder.ivCurrencyIcon)
        } else {
            Log.d("WalletAdapter", "Loading from web")

            if ((Date().time - currencyList.lastUpdated.time) > 15*60*1000) {
                storageManager.clearCurrencyCache()
            }

            mCurrencyService.getCurrencyById(wallet.id).enqueue(object : Callback<Currency> {
                override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                    if (response.isSuccessful) {
                        val theCurrency = response.body()
                        holder.tvCurrencyName.text = theCurrency.name
                        if (theCurrency.name == "Coin") {
                            var value = wallet.value
                            val gold = value / 10000
                            value %= 10000
                            val silver = value / 100
                            val copper = value % 100
                            holder.tvCurrencyAmount.text = "${gold}g ${silver}s ${copper}c"
                        } else
                            holder.tvCurrencyAmount.text = wallet.value.toString()

                        Glide.with(mContext).load(theCurrency.icon).into(holder.ivCurrencyIcon)
                        storageManager.saveCurrencyList(theCurrency)
                    }
                }

                override fun onFailure(call: Call<Currency>, t: Throwable) {

                }
            })
        }
    }

    override fun getItemCount(): Int {
        return mWallet.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvCurrencyName: TextView by bindView(R.id.tvCurrencyName)
        val tvCurrencyAmount: TextView by bindView(R.id.tvCurrencyAmount)
        val ivCurrencyIcon: ImageView by bindView(R.id.ivCurrencyIcon)
    }
}
