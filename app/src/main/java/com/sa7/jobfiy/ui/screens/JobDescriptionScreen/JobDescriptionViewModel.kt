package com.sa7.jobfiy.ui.screens.JobDescriptionScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa7.jobfiy.api.JobSearch
import com.sa7.jobfiy.api.SampleJobs
import kotlinx.coroutines.launch

class JobDescriptionViewModel : ViewModel() {
    private val _jobDetails = MutableLiveData<JobSearch?>()
    val jobDetails: LiveData<JobSearch?>
        get() = _jobDetails

    fun getJobDetails(jobId: String?) {
        viewModelScope.launch {
            try {
                if (jobId == null) {
                    _jobDetails.value = null
                    return@launch
                }
                
                val job = SampleJobs.getSampleJobs().find { it.id == jobId }
                _jobDetails.postValue(job)
            } catch (e: Exception) {
                _jobDetails.value = null
                Log.e("JobDescriptionViewModel", "Error loading job details: ${e.message}", e)
            }
        }
    }
}