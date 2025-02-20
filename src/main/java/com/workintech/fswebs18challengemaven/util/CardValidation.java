package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CardValidation {

    public static void isIdValid(Long id)
    {
        if(id == null || id <= 0)
        {
            throw new CardException("Id is not null or equal or lower than zero", HttpStatus.BAD_REQUEST);
        }
    }

    public static void isCardNull(Card card)
    {
        if(card == null)
        {
            throw new CardException("Card is not null",HttpStatus.NOT_FOUND);
        }
    }

    public static void cardTypeControl(List<Card> cards)
    {
        if(cards != null)
        {
            for (int i = 0; i < cards.size(); i++) {
                if(cards.get(i).getType() != null)
                {
                    if(cards.get(i).getValue() != null)
                    {
                        throw new CardException("if type has value, value should be null",HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }
    }

    public static void jokerTypeCardControl(List<Card> cards)
    {
        if(cards != null)
        {
            for (int i = 0; i < cards.size(); i++) {
                if(cards.get(i).getType().equals("JOKER"))
                {
                    if(cards.get(i).getValue() != null || cards.get(i).getColor() != null){
                        throw new CardException("When type equal JOKER, value and color must be null",HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }
    }

    public static void cardValueControl(List<Card> cards)
    {
        if(cards != null)
        {
            for (int i = 0; i < cards.size(); i++) {
                if(cards.get(i).getValue() != null)
                {
                    if(cards.get(i).getType() != null)
                    {
                        throw new CardException("if value has value, type should be null",HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }
    }

    public static void cardListSizeControl(List<Card> cardList)
    {
        if(cardList.isEmpty())
        {
            throw new CardException("Card size empty",HttpStatus.BAD_REQUEST);
        }
    }

    public static void oneCardTypeControl(Card card)
    {
        if(card != null)
        {
            if(card.getType() != null)
            {
                if(card.getValue() != null)
                {
                    throw new CardException("if type has type, value must be null",HttpStatus.BAD_REQUEST);
                }
            }
        }
    }

    public static void oneCardValueControl(Card card)
    {
        if(card != null)
        {
            if(card.getValue() != null)
            {
                if(card.getType() != null)
                {
                    throw new CardException("if value has value, type must be null",HttpStatus.BAD_REQUEST);
                }
            }
        }
    }

    public static void oneCardJokerTypeControl(Card card)
    {
        if(card != null)
        {
            if(card.getType().equals("JOKER"))
            {
                if(card.getValue() != null || card.getColor() != null)
                {
                    throw new CardException("When type equal JOKER, value and color must be null",HttpStatus.BAD_REQUEST);
                }
            }
        }
    }
}