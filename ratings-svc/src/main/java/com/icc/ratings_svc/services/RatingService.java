package com.icc.ratings_svc.services;

import com.icc.ratings_svc.models.Rating;
import com.icc.ratings_svc.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getRatingsByStore(Long storeId) {
        return ratingRepository.findByStoreId(storeId);
    }

    public Rating addRating(Rating rating) {
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    public boolean hasRatingForOrder(Long orderId, Long storeId) {
        return ratingRepository.existsByOrderIdAndStoreId(orderId, storeId);
    }

}

