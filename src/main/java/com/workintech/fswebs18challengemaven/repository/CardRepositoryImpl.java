package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepositoryImpl implements CardRepository{

    private EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Card save(Card card) {
        CardValidation.isCardNull(card);
        CardValidation.oneCardTypeControl(card);
        CardValidation.oneCardValueControl(card);
        CardValidation.oneCardJokerTypeControl(card);
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :color",Card.class);
        query.setParameter("color",color);
        CardValidation.cardListSizeControl(query.getResultList());
        return query.getResultList();
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c",Card.class);
        return query.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.value = :value",Card.class);
        query.setParameter("value",value);
        CardValidation.cardListSizeControl(query.getResultList());
        CardValidation.cardValueControl(query.getResultList());
        CardValidation.jokerTypeCardControl(query.getResultList());
        return query.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :type",Card.class);
        query.setParameter("type",type);
        CardValidation.cardListSizeControl(query.getResultList());
        CardValidation.cardTypeControl(query.getResultList());
        CardValidation.jokerTypeCardControl(query.getResultList());
        return query.getResultList();
    }

    @Transactional
    @Override
    public Card update(Card card) {
        CardValidation.isCardNull(card);
        CardValidation.oneCardTypeControl(card);
        CardValidation.oneCardValueControl(card);
        CardValidation.oneCardJokerTypeControl(card);
        entityManager.merge(card);
        return card;
    }

    @Transactional
    @Override
    public Card remove(Long id) {
        CardValidation.isIdValid(id);
        Card card = entityManager.find(Card.class,id);
        CardValidation.isCardNull(card);
        entityManager.remove(card);
        return card;
    }
}