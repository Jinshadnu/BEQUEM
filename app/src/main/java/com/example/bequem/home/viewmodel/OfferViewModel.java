package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bequem.home.pojo.OfferResponse;
import com.example.bequem.home.repository.OfferRepository;

public class OfferViewModel extends ViewModel {
    public OfferRepository offerRepository;

    public OfferViewModel() {
        this.offerRepository = new OfferRepository();
    }

    public LiveData<OfferResponse> getOffers(){
        return offerRepository.getOffers();
    }
}
