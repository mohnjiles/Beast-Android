package xyz.jtmiles.beastforgw2.managers

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import xyz.jtmiles.beastforgw2.models.*
import xyz.jtmiles.beastforgw2.models.Currency
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

class StorageManager(private val mContext: Context){

    fun saveCharacters(characters: List<Character>){
        val fileName = "characters.json"
        val gson = Gson()
        val savedCharacterList = SavedCharacterList(characterList = characters, lastUpdated = Date())
        val json = gson.toJson(savedCharacterList)

        try {
            val outputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE)
            outputStream.write(json.toByteArray())
            outputStream.close()
        } catch (ex: Exception){
            Log.w("StorageManager", "Error creating file: ${ex.message}")
        }
    }

    fun loadCharacters(): SavedCharacterList {
        val fileName = "characters.json"
        val gson = Gson()
        val sb = StringBuilder()

        try {
            val file = File("${mContext.filesDir.absolutePath}/$fileName")
            val bufferedReader = BufferedReader(FileReader(file))
            var line = bufferedReader.readLine()
            while(line != null) {
                sb.append(line)
                line = bufferedReader.readLine()
            }
            bufferedReader.close()

        } catch (ex: Exception){
            Log.w("StorageManager", "Error loading characters")
        }

        val listType = object: TypeToken<SavedCharacterList>() {}.type

        return gson.fromJson<SavedCharacterList>(sb.toString(), listType)
    }

    fun saveWallet(wallet: List<Wallet>) {
        val fileName = "wallet.json"
        val gson = Gson()
        val savedWallet = CachedWalletList(wallet = wallet, lastUpdated = Date())
        val json = gson.toJson(savedWallet)

        try {
            val outputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE)
            outputStream.write(json.toByteArray())
            outputStream.close()
        } catch (ex: Exception){
            Log.w("StorageManager", "Error creating file: ${ex.message}")
        }
    }

    fun loadWallet(): CachedWalletList {
        val fileName = "wallet.json"
        val gson = Gson()
        val sb = StringBuilder()

        try {
            val file = File("${mContext.filesDir.absolutePath}/$fileName")
            val bufferedReader = BufferedReader(FileReader(file))
            var line = bufferedReader.readLine()
            while(line != null) {
                sb.append(line)
                line = bufferedReader.readLine()
            }
            bufferedReader.close()

        } catch (ex: Exception){
            Log.w("StorageManager", "Error loading wallet")
        }

        val listType = object: TypeToken<CachedWalletList>() {}.type

        return gson.fromJson<CachedWalletList>(sb.toString(), listType)
    }

    fun saveCurrencyList(currency: Currency) {
        val fileName = "currency.json"
        val gson = Gson()

        val savedCurrencyList = ArrayList(loadCurrencyList().currencyList)
        if (!savedCurrencyList.contains(currency)) {
            savedCurrencyList.add(currency)
        }

        val cachedCurrencyList = CachedCurrencyList(currencyList = savedCurrencyList, lastUpdated = Date())
        val json = gson.toJson(cachedCurrencyList)

        try {
            val outputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE)
            outputStream.write(json.toByteArray())
            outputStream.close()
        } catch (ex: Exception){
            Log.w("StorageManager", "Error creating file: ${ex.message}")
        }
    }

    fun loadCurrencyList() : CachedCurrencyList {
        val fileName = "currency.json"
        val gson = Gson()
        val sb = StringBuilder()

        try {
            val file = File("${mContext.filesDir.absolutePath}/$fileName")
            val bufferedReader = BufferedReader(FileReader(file))
            var line = bufferedReader.readLine()
            while(line != null) {
                sb.append(line)
                line = bufferedReader.readLine()
            }
            bufferedReader.close()

        } catch (ex: Exception){
            Log.w("StorageManager", "Error loading wallet")
        }

        val listType = object: TypeToken<CachedCurrencyList>() {}.type

        val currencyList = gson.fromJson<CachedCurrencyList>(sb.toString(), listType)
                ?: return CachedCurrencyList(currencyList = ArrayList<Currency>(), lastUpdated = Date())

        return currencyList
    }

    fun clearCurrencyCache() {
        val fileName = "currency.json"
        val gson = Gson()

        val cachedCurrencyList = CachedCurrencyList(currencyList = ArrayList<Currency>(), lastUpdated = Date())
        val json = gson.toJson(cachedCurrencyList)

        try {
            val outputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE)
            outputStream.write(json.toByteArray())
            outputStream.close()
        } catch (ex: Exception){
            Log.w("StorageManager", "Error creating file: ${ex.message}")
        }
    }

    fun loadBank() : CachedBankItems {
        val fileName = "bank.json"
        val gson = Gson()
        val sb = StringBuilder()

        try {
            val file = File("${mContext.filesDir.absolutePath}/$fileName")
            val bufferedReader = BufferedReader(FileReader(file))
            var line = bufferedReader.readLine()
            while(line != null) {
                sb.append(line)
                line = bufferedReader.readLine()
            }
            bufferedReader.close()

        } catch (ex: Exception){
            Log.w("StorageManager", "Error loading wallet")
        }

        val listType = object: TypeToken<CachedBankItems>() {}.type

        return gson.fromJson<CachedBankItems>(sb.toString(), listType)
    }

    fun saveBank(bankItems: List<Inventory>) {
        val fileName = "bank.json"
        val gson = Gson()

        val cachedBankItems = CachedBankItems(bankItems = bankItems, lastUpdated = Date())
        val json = gson.toJson(cachedBankItems)

        try {
            val outputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE)
            outputStream.write(json.toByteArray())
            outputStream.close()
        } catch (ex: Exception){
            Log.w("StorageManager", "Error creating file: ${ex.message}")
        }
    }
}