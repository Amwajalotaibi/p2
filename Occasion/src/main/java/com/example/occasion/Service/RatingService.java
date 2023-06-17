package com.example.occasion.Service;

import com.example.occasion.ApiException.ApiException;
import com.example.occasion.DTO.CustomerDTO;
import com.example.occasion.DTO.RatingDTO;
import com.example.occasion.Model.*;
import com.example.occasion.Repostiroy.MyorderRepository;
import com.example.occasion.Repostiroy.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final MyorderRepository myorderRepository;

    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    public void addRating(RatingDTO dto) {
        Myorder myorder = myorderRepository.findMyorderById(dto.getRating_id());
        if (myorder == null) {
            throw new ApiException("sorry can't add");
        }
        Rating rating = new Rating(null, dto.getName(), null);
        ratingRepository.save(rating);
    }


    public void updateRating(RatingDTO dto) {
        Myorder myorder = myorderRepository.findMyorderById(dto.getRating_id());
        if (myorder == null) {
            throw new ApiException("MyOrder not found");
        }

        Rating rating = ratingRepository.getReferenceById(dto.getRating_id());
        rating.setName(dto.getName());
    }

    public void deleteRating(Integer id) {
        Rating rating = ratingRepository.findRatingById(id);
        if (rating == null) {
            throw new ApiException("Not found");
        }
        ratingRepository.delete(rating);
    }
}
