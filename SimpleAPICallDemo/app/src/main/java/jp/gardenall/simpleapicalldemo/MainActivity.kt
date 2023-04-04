package jp.gardenall.simpleapicalldemo

import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CallAPILoginAsyncTask(username = "denis", password = "123456").startApiCall()
    }

    private inner class CallAPILoginAsyncTask(val username: String, val password: String){
        private lateinit var customProgressDialog: Dialog

        fun startApiCall() {
            showProgressDialog()
            lifecycleScope.launch(Dispatchers.IO) {
//                delay(5000L)
                val stringResult=makeApiCall()
                afterCallFinish(stringResult)
            }
        } // これがinstructorのonPreExecuteに対応している

        fun makeApiCall(): String {
            var result:String
            var connection: HttpURLConnection?=null

            try{
                val url=URL("https://run.mocky.io/v3/dd3b2baf-0e16-4b58-846d-9a7e77166f21")
                connection= url.openConnection() as HttpURLConnection?   //Returns a URLConnection instance that represents a connection to the remote object referred to by the URL.
                connection!!.doInput=true  //doInput tells if we get any data(by default doInput will be true and doOutput false)
                connection!!.doOutput=true //doOutput tells if we send any data with the api call

                connection.instanceFollowRedirects = false

                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.setRequestProperty("charset", "utf-8")
                connection.setRequestProperty("Accept", "application/json")
                connection.useCaches = false

                val writeDataOutputStream = DataOutputStream(connection.outputStream)
                val jsonRequest = JSONObject()
                jsonRequest.put("username", username)
                jsonRequest.put("password", password)

                writeDataOutputStream.writeBytes(jsonRequest.toString())
                writeDataOutputStream.flush()
                writeDataOutputStream.close()

                val httpResult:Int=connection.responseCode
                if(httpResult==HttpURLConnection.HTTP_OK){
                    //now once we have established a successful connection, we want to read the data.

                    //Returns an input stream that reads from this open connection. A SocketTimeoutException can be thrown when
                    // reading from the returned input stream if the read timeout expires before data is available for read.
                    val inputStream=connection.inputStream

                    val reader=BufferedReader(InputStreamReader(inputStream))
                    val stringBuilder:StringBuilder=StringBuilder()
                    var line:String?
                    try{
                        while (reader.readLine().also { line=it }!=null) {
                            stringBuilder.append(line+"\n")
                            Log.i("TAG", "doInBackground: $line\n")
                        }
                    }
                    catch (e:IOException){
                        e.printStackTrace()
                    }
                    finally {
                        try {  //there could be some error while closing the inputStream
                            inputStream.close()
                        }
                        catch (e:IOException){
                            e.printStackTrace()
                        }
                    }
                    result=stringBuilder.toString()
                }
                else{  //if the response code is not OK
                    result=connection.responseMessage
                }
            }
            catch (e:SocketTimeoutException){
                result="Connection Timeout"
            }
            catch (e:Exception){
                result="Error + ${e.message}"
            }
            finally {
                connection?.disconnect()
            }

            return result
        }

        fun afterCallFinish(result: String?) {
            cancelProgressDialog()

            Log.i("JSON RESPONSE RESULT", result.toString())

            val responseData = Gson().fromJson(result, ResponseData::class.java)
            Log.i("Message", responseData.message)

            Log.i("Is Profile Completed", "${responseData.profile_details.is_profile_completed}")

            for (item in responseData.data_list.indices) {
                Log.i("Value $item", "${responseData.data_list[item]}")

                Log.i("ID", "${responseData.data_list[item].id}")
            }

            val jsonObject = JSONObject(result)
            val message = jsonObject.optString("message")
            Log.i("Message", message)
            val userId = jsonObject.optString("user_id")
            Log.i("User Id", "$userId")
            val name = jsonObject.optString("name")
            Log.i("Name", "$name")

            val profileDetailsObject = jsonObject.optJSONObject("profile_details")
            val isProfileCompleted = profileDetailsObject.optBoolean("is_profile_completed")
            Log.i("Is Profile Completed", "$isProfileCompleted")

            val dataListArray = jsonObject.optJSONArray("data_list")
            Log.i("Data List Size", "${dataListArray.length()}")

            for(item in 0 until dataListArray.length()) {
                Log.i("Value $item", "${dataListArray[item]}")

                val dataItemObject: JSONObject = dataListArray[item] as JSONObject
                val id = dataItemObject.optInt("id")
                Log.i("ID", "$id")

                val value = dataItemObject.optString("value")
                Log.i("Value", "$value")
            }
        } // これがinstructorのonPostExecuteに対応している

        private fun showProgressDialog(){
            customProgressDialog= Dialog(this@MainActivity)
            customProgressDialog.setContentView(R.layout.dialog_custom_progress)
            customProgressDialog.show()
        }

        private fun cancelProgressDialog(){
            customProgressDialog.dismiss()
        }
    }
}