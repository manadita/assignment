package edu.neu.madcourse.numad21fa_yuzou;

public class LinkCard implements ILinkCard{
    private String linkname;
    private String linkulr;

    /**
     * Constructor for a link card.
     * @param name  the name of the link.
     * @param url   the url of the link.
     */
    public LinkCard(String name, String url){
        if (name != null && url != null){
            this.linkname = name;
            this.linkulr = url;
        }
        else {
            if (name == null) {
                this.linkname = "N/A";
            }
            if (url == null) {
                this.linkulr = "N/A";
            }
        }
    }

    @Override
    public String getName() {
        return this.linkname;
    }

    @Override
    public String getURL() {
        return this.linkulr;
    }

    @Override
    public void saveName(String name) {
        this.linkname = name;
    }

    @Override
    public void saveURL(String url) {
        this.linkulr = url;
    }
}
