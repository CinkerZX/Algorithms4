//import com.sun.org.apache.xpath.internal.operations.String;
import sun.misc.Queue;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Listingfiles<Item>{
    CopyQueue<Item> fileNames;
    java.lang.String folderName;
    static String path = "C:/Users/Cinker/Music/";
    int layer;

    public Listingfiles(String nameFolder){
        fileNames = new CopyQueue<>();
        path = path.concat(nameFolder);
        folderName = nameFolder;
        layer = 1;
    }

    public Listingfiles(String nameFolder, String newPath, int paLayer){
        layer = paLayer+1;
        fileNames = new CopyQueue<>();
        path = newPath;
        folderName = nameFolder;
    }

    public void CollectandPrintFiles(){
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        printOutFolderName(this.layer);
        for (File file : listOfFiles) {
            if (file.isFile()) { // read files
                String i = file.getName();
                fileNames.add((Item) i);
            }
            if (file.isDirectory()){ // read folders
                String a = file.getName();
                Listingfiles subFile = new Listingfiles(a,file.getPath(),this.layer);
                subFile.CollectandPrintFiles();
            }
        }
        printOutQueue(this.layer-1);
    }

    public void printOutFolderName(int i){
        String space = " ";
        for (int j = 1; j < i; j++) {
            space = space+" ";
        }
        String folderN = space.concat(this.folderName);
        System.out.println(folderN);
    }

    public void printOutQueue(int i){
        String space = " ";
        for (int j = 1; j < i; j++) {
            space = space+" ";
        }
        if (!fileNames.myQueue.isEmpty()){
            for (Object name:fileNames.myQueue) {
                System.out.println(space+"  "+name);
            }
//            Iterator<Item> iter = fileNames.iterator();
//            iter.forEachRemaining(System.out::println);
        }
    }

    public static void main(java.lang.String[] args) {
        Listingfiles myFiles = new Listingfiles(args[0]);
        myFiles.CollectandPrintFiles();
    }
}
