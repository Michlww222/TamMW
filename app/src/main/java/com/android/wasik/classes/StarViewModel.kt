import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.wasik.classes.StarRepository
import com.android.wasik.data.Starship
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StarViewModel : ViewModel() {

    private val starRepository = StarRepository()

    private val mutableStarshipsData = MutableLiveData<List<Starship>>()
    val immutableStarshipsData: LiveData<List<Starship>> = mutableStarshipsData

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = starRepository.getStarResponse()
                val starships = request.body()?.results
                mutableStarshipsData.postValue(starships!!)

            } catch (e: Exception) {
                Log.e("MainViewModel", "Operacja nie powiodla sie", e)
            }
        }
    }
}