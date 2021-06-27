package com.webtech.database.service;

import com.webtech.database.model.IndexCard;
import com.webtech.database.repository.IndexCardRepository;
import com.webtech.database.repository.UserRepository;
import com.webtech.exceptions.NotFoundException;
import com.webtech.infocard.responsemodel.IndexCardResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexCardService {

    @Autowired
    private IndexCardRepository cardRepo;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserRepository userRepo;

    private IndexCard findCardById(Long indexCardId) {
        return cardRepo.findById(indexCardId).orElseThrow(() -> new NotFoundException("IndexCardId " + indexCardId + " does not exist!"));
    }

    public IndexCardResponse addCard(Long topicId, IndexCardResponse cardRes) {
        IndexCard newCard = cardRepo.save(new IndexCard(topicService.findTopicById(topicId), cardRes.getQuestion(), cardRes.getAnswer(), cardRes.isFavored()));
        return new IndexCardResponse(newCard.getId(), newCard.getQuestion(), newCard.getAnswer(), newCard.isFavored());
    }

    public List<IndexCardResponse> getAllCardsByTopicId(Long topicId) {
        List<IndexCard> indexCardList = cardRepo.findAllCardsByTopicId(topicService.findTopicById(topicId).getId());
        return indexCardList.stream()
        .map(i -> new IndexCardResponse(i.getId(), i.getQuestion(), i.getAnswer(), i.isFavored()))
        .collect(Collectors.toList());
    }

    public void deleteCard(List<Long> cardIdList) {
        cardIdList.stream()
        .forEach(id -> {
            if (cardRepo.existsById(id)) {
                cardRepo.deleteById(id);
            }
        });
    }

    public IndexCardResponse updateCard(IndexCardResponse cardRes) {
        IndexCard card = findCardById(cardRes.getId());
        if(!card.getQuestion().equals(cardRes.getQuestion())) {
            card.setQuestion(cardRes.getQuestion());
        } else if(!card.getAnswer().equals(cardRes.getAnswer())) {
            card.setAnswer(cardRes.getAnswer());
        } else {
            card.setIsFavored(cardRes.isFavored());
        }
        cardRepo.save(card);
        return cardRes;
    }

    public List<IndexCardResponse> findAllFavorites(String userId) {
        if (userRepo.existsById(userId)) {
            List<IndexCard> cardsList = cardRepo.findFavoriteCards(userId);
            return cardsList.stream()
        .map(i -> new IndexCardResponse(i.getId(), i.getQuestion(), i.getAnswer(), i.isFavored()))
        .collect(Collectors.toList());
        }
       return new ArrayList<IndexCardResponse>();
    }
}
