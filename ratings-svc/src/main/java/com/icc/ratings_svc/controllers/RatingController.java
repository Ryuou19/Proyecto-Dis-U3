package com.icc.ratings_svc.controllers;

import com.icc.ratings_svc.domain.RatingResponse;
import com.icc.ratings_svc.models.Rating;
import com.icc.ratings_svc.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
@CrossOrigin(origins = "http://localhost:4200")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<Object> getRatingsByStore(@RequestParam Long storeId) {
        // Obtener calificaciones de la tienda
        List<Rating> ratings = ratingService.getRatingsByStore(storeId);

        if (ratings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No ratings found for store with ID " + storeId);
        }

        // Calcular la calificación promedio
        double averageRating = ratings.stream().mapToInt(Rating::getRating).average().orElse(0);

        // Crear un objeto de respuesta que incluya la calificación promedio y las calificaciones
        return ResponseEntity.ok(new RatingResponse(averageRating, ratings));
    }

    // En RatingController.java, añade este nuevo endpoint
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkOrderRating(@RequestParam Long orderId, @RequestParam Long storeId) {
        boolean hasRating = ratingService.hasRatingForOrder(orderId, storeId);
        return ResponseEntity.ok(hasRating);
    }



    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        if (rating.getStoreId() == null || rating.getOrderId() == null || rating.getRating() < 0 || rating.getRating() > 5 || rating.getComment() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Rating savedRating = ratingService.addRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRating);
    }

}
