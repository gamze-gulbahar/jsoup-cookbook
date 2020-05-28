import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class CookBook {

    static void parseDocument(String html){
        System.out.println("Parse a document from a String");
        Document doc = Jsoup.parse(html);
        System.out.println(doc);
        System.out.println("--------------");

    }
    static void parseDocumentBody(String html){
        System.out.println("Parse a body fragment");
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        System.out.println(body);
        System.out.println("--------------");
    }

    static void loadDocumentFromURL(String url) throws IOException {
        System.out.println("Load a Document from an URL ");
        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        System.out.println(title);
        System.out.println("--------------");
    }

    static void loadDocumentFromFile() throws IOException {
        System.out.println("Load a Document from a File ");
        File input = new File("./tmp/input.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        System.out.println(doc);
        System.out.println("--------------");

    }

    static void useDOM() throws IOException {
        System.out.println("Use DOM methods to navigate a document");
        File input = new File("./tmp/input.html");
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Element content = doc.getElementById("content");
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            System.out.println("Link: " +linkHref);
            String linkText = link.text();
            System.out.println("Link Text: " +linkText);
        }
        System.out.println("--------------");
    }

    static void useSelector() throws IOException {
        File input = new File("./tmp/input.html");
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

        Elements links = doc.select("a[href]"); // a with href
        System.out.println(links);
        Elements pngs = doc.select("img[src$=.png]");
        // img with src ending .png
        System.out.println(pngs);

        Element masthead = doc.select("div.masthead").first();
        // div with class=masthead
        System.out.println(masthead);

        Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
        System.out.println("--------------");
    }
    static void extractingFromElements(){
        String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
        Document doc = Jsoup.parse(html);
        System.out.println("Doc: \n" + doc);
        Element link = doc.select("a").first();
        System.out.println("Link: " +link);

        String text = doc.body().text(); // "An example link"
        System.out.println("Body text: "+text);
        String linkHref = link.attr("href"); // "http://example.com/"
        System.out.println("Link href: " + linkHref);
        String linkText = link.text(); // "example""
        System.out.println("Link Text: " + linkText);

        String linkOuterH = link.outerHtml();
        // "<a href="http://example.com"><b>example</b></a>"
        System.out.println("Link Outer HTML: " + linkOuterH);
        String linkInnerH = link.html(); // "<b>example</b>"
        System.out.println("Link Inner HTML: "+ linkInnerH);
        System.out.println("--------------");
    }

    static void workingWithURLs() throws IOException {
        Document doc = Jsoup.connect("http://jsoup.org").get();
        Element link = doc.select("a").first();
        System.out.println("Link: " + link);
        String relHref = link.attr("href"); // == "/"
        System.out.println("Relative URL: " + relHref);
        String absHref = link.attr("abs:href"); // "http://jsoup.org/"
        System.out.println("Absolute Link: " + absHref);
        System.out.println("--------------");
    }




    public static void main (String[]args) throws IOException {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</body></html>";
        parseDocument(html);

        String neuHtml = "<div><p>Lorem ipsum.</p><head>HTML</head>";
        parseDocumentBody(neuHtml);

        String url = "http://example.com/";
        loadDocumentFromURL(url);

        loadDocumentFromFile();
        useDOM();
        useSelector();
        extractingFromElements();
        workingWithURLs();



    }
}
