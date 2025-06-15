package com.sa7.jobfiy.ui.screens.HomeScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa7.jobfiy.api.Jobs
import com.sa7.jobfiy.api.SampleJobs
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)
    var isDataLoaded by mutableStateOf(false)
    var selectedJob by mutableStateOf<String?>(null)

    private val _data = MutableLiveData<Jobs?>()
    val data: LiveData<Jobs?>
        get() = _data

    // Fetch job data with error handling
    fun getJobsForCard(query: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                val sampleJobs = SampleJobs.getSampleJobs()
                val filteredJobs = if (query.isNotEmpty() && query != "all") {
                    sampleJobs.filter { job ->
                        job.title.contains(query, ignoreCase = true) ||
                                job.company_name.contains(query, ignoreCase = true) ||
                                job.location.contains(query, ignoreCase = true)
                    }
                } else {
                    sampleJobs
                }

                _data.postValue(Jobs(count = filteredJobs.size, hits = filteredJobs))
                isDataLoaded = true
                isLoading = false
            } catch (e: Exception) {
                isDataLoaded = true
                isLoading = false
                _data.value = null
                Log.e("HomeScreenViewModel", "Error loading jobs: ${e.message}", e)
            }
        }
    }
}