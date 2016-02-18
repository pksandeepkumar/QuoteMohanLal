package texus.datamodel;

/**
 * Created by sandeep on 17/2/16.
 */
public class WikiData {

    public final static int IMAGE_SIZE_SMALL = 100;
    public final static int IMAGE_SIZE_MEDIUM = 300;
    public final static int IMAGE_SIZE_LARGE = 500;
    public final static int IMAGE_SIZE_EXTRA_LARGE = 800;

    public String wiki_url = "";
    public String article_name = "";



    public WikiData( String url) {
        wiki_url = url;
        extractArticleName();
    }


    /**
     * https://en.wikipedia.org/w/api.php?action=query&titles=Mohanlal&prop=pageimages&pithumbsize=300
     * @param thumbSize
     * @return
     */
    public String getRequestURL(int thumbSize) {
        return "https://en.wikipedia.org/w/api.php?action=query&titles=" +
                article_name +
                "&prop=pageimages&pithumbsize=" +
                thumbSize;
    }


    public void extractArticleName() {
        if(wiki_url == null) return;
        String[] split = wiki_url.split("/");
        int splitSize = split.length;
        if(splitSize > 1) {
            article_name = split[ splitSize - 1];
        }
    }

//    https://en.wikipedia.org/w/api.php?action=query&titles=Mohanlal&prop=pageimages&pithumbsize=300

//    {
//        "batchcomplete": "",
//            "query": {
//        "pages": {
//            "478225": {
//                "pageid": 478225,
//                        "ns": 0,
//                        "title": "Mohanlal",
//                        "thumbnail": {
//                    "source": "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/Mohanlal_Viswanathan_Nair_BNC.jpg/257px-Mohanlal_Viswanathan_Nair_BNC.jpg",
//                            "width": 257,
//                            "height": 300
//                },
//                "pageimage": "Mohanlal_Viswanathan_Nair_BNC.jpg"
//            }
//        }
//    }
//    }


//    {
//    "batchcomplete": "",
//            "query": {
//        "normalized": [
//        {
//            "from": "Jerry_Amaldev",
//                "to": "Jerry Amaldev"
//        }
//        ],
//        "pages": {
//            "28400813": {
//                "pageid": 28400813,
//                        "ns": 0,
//                        "title": "Jerry Amaldev"
//            }
//        }
//    }
//}

}
