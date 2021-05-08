package com.webtech.database.service;

import com.webtech.database.model.IndexCard;
import com.webtech.database.repository.IndexCardRepository;
import com.webtech.exceptions.NotFoundException;
import com.webtech.infocard.IndexCardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class IndexCardService {

    @Autowired
    IndexCardRepository indexCardRepo;

    @Autowired
    TopicService topicService;

    public IndexCard findIndexCardById(Long indexCardId) {
        return indexCardRepo.findById(indexCardId).orElseThrow(() -> new NotFoundException("IndexCardId " + indexCardId + " does not exist!"));
    }

    public IndexCardResponse addIndexCard(Long topicId, IndexCardResponse indexCardResponse) {
        IndexCard newIndexCard = new IndexCard(topicService.findTopicById(topicId), indexCardResponse.getQuestion(), indexCardResponse.getAnswer());
        indexCardRepo.save(newIndexCard);
        return new IndexCardResponse(newIndexCard.getId(), newIndexCard.getQuestion(), newIndexCard.getAnswer());
    }

    public List<IndexCardResponse> getAllIndexCardsFromTopic(Long topicId) {
        List<IndexCardResponse> topicList = new ArrayList<>();
        Set<IndexCard> topicSet = topicService.findTopicById(topicId).getIndexCards();
        for (IndexCard index : topicSet) {
            topicList.add(new IndexCardResponse(index.getId(), index.getQuestion(), index.getAnswer()));
        }
        return topicList;
    }

    public void deleteIndexCard(List<IndexCardResponse> indexCardResponseList) {
        for (IndexCardResponse cardR : indexCardResponseList) {
            if (indexCardRepo.existsById(cardR.getId())) {
                indexCardRepo.delete(findIndexCardById(cardR.getId()));
            }
        }
    }

    public IndexCardResponse renameIndexCard(IndexCardResponse indexCardResponse) {
        IndexCard indexCard = findIndexCardById(indexCardResponse.getId());
        indexCard.setQuestion(indexCardResponse.getQuestion());
        indexCard.setAnswer(indexCardResponse.getAnswer());
        indexCardRepo.save(indexCard);
        return indexCardResponse;
    }
}
