package edu.neu.madcourse.numad21fa_yuzou;

/**
 * Interface of a link card.
 */
public interface ILinkCard {

    public String getName();

    public String getURL();

    public void saveName(String name);
    
    public void saveURL(String url);
}
