package edu.neu.madcourse.numad21fa_yuzou;

/**
 * Interface of a link card.
 */
public interface ILinkCard {

    /**
     * Return the name of a link.
     * @return  name.
     */
    public String getName();

    /**
     * Retruen the url of a link.
     * @return  url.
     */
    public String getURL();

    /**
     * Edit the name of a link.
     * @param name  input name.
     */
    public void saveName(String name);

    /**
     * Edit the url of a link.
     * @param url   input url.
     */
    public void saveURL(String url);
}
