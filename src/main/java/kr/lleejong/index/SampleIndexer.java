package kr.lleejong.index;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by lleejong on 2017. 6. 4..
 */
public class SampleIndexer {
    private String indexPath;
    private IndexWriter writer;
    
    private ArrayList<File> indexQueue;
    
    public SampleIndexer(String indexPath) throws IOException {
        this.indexPath = indexPath;
        this.indexQueue = new ArrayList<File>();
        Directory directory = FSDirectory.open(new File(indexPath).toPath());
        
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        //IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer(Version.LUCENE_6_5_1));
        writer = new IndexWriter(directory, config);
    }
    
    public void indexFileOrDirectory(String path){
        addTargetFiles(path);
        
        int originDocNum = writer.numDocs();
        for(File file : indexQueue){
            FileReader fileReader = null;
            
        }
    }
    
    private void addTargetFiles(String path){
        File root = new File(path);
        if(root.isDirectory()){
            File[] files = root.listFiles();
            for(File file : files){
               addTargetFiles(file.getAbsolutePath());
            }
        }
        else{
            indexQueue.add(root);
        }
    }
}
