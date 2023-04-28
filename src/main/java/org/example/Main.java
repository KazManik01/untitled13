package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://kinescope.io/06fc1552-6651-43ef-a5c3-97f33f9dd657/master.mpd").get();
        ArrayList<String> links = new ArrayList<>(10);
        int i = 1;
        int schet = 0;
        System.out.println("720p - 0, 1080p - 1,360p - 2,480p - 3,");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        String jj = "!";
        while (!jj.isEmpty()) {
            String link = "Representation[height=360] > SegmentList > SegmentURL:eq(" + i + ")";
            jj = new String((doc.select(link).attr("media")));
            links.add(jj);
            i++;
            schet++;
        }

        Set<String> linkSet = new HashSet<>(links);
        links.clear();
        links.addAll(linkSet);
        links.forEach(System.out::println);
        download(links);


    }

    public static void download(ArrayList<String> links) throws IOException {
        for (int i = 1; i < links.size(); i++) {
            FileUtils.copyURLToFile(new URL(links.get(i)), new File("C:/Users/Rybin/test"+ i +".mp4"));
            if (links.get(i).isEmpty()){
                break;
            }


        }
    }

}