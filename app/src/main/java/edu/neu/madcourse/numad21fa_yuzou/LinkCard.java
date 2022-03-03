package edu.neu.madcourse.numad21fa_yuzou;

public class LinkCard implements ILinkCard{
    private String linkName;
    private String linkURL;

    /**
     * Constructor for a link card.
     * @param name  the name of the link.
     * @param url   the url of the link.
     */
    public LinkCard(String name, String url){
        if (name != null && url != null){
            this.linkName = name;
            this.linkURL = url;
        }
        else {
            if (name == null) {
                this.linkName = "N/A";
            }
            if (url == null) {
                this.linkURL = "N/A";
            }
        }
    }

    @Override
    public String getName() {
        return this.linkName;
    }

    @Override
    public String getURL() {
        return this.linkURL;
    }

    @Override
    public void saveName(String name) {
        this.linkName = name;
    }

    @Override
    public void saveURL(String url) {
        this.linkURL = url;
    }
}
