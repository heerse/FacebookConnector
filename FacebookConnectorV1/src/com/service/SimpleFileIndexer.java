package com.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.*;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.*;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SimpleFileIndexer {


	public static void main(String[] args) throws Exception {
		
		File indexDir = new File("C:\\Users\\HEER\\Desktop\\zubair123\\Index");
		File dataDir = new File("C:\\Users\\HEER\\Desktop\\zubair123");
		String suffix = "txt";
		
		SimpleFileIndexer indexer = new SimpleFileIndexer();
		
		int numIndex = indexer.index(indexDir, dataDir, suffix);
		
		System.out.println("Total files indexed " + numIndex);
		
	}
	
	public int index(File indexDir, File dataDir, String suffix) throws Exception {
		
		IndexWriter indexWriter = new IndexWriter(
				FSDirectory.open(indexDir),
				new SimpleAnalyzer(),
				true,
				MaxFieldLength.UNLIMITED);
		indexWriter.setUseCompoundFile(false);
		
		indexDirectory(indexWriter, dataDir, suffix);
		
		int numIndexed = indexWriter.maxDoc();
		indexWriter.optimize();
		indexWriter.close();
		
		return numIndexed;
		
	}
	
	public void indexDirectory(IndexWriter indexWriter, File dataDir, String suffix) throws IOException {
		File[] files = dataDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(indexWriter, f, suffix);
			}
			else {
				indexFileWithIndexWriter(indexWriter, f, suffix);
			}
		}
	}
	
	public void indexFileWithIndexWriter(IndexWriter indexWriter, File f, String suffix) throws IOException {
		if (f.isHidden() || f.isDirectory() || !f.canRead() || !f.exists()) {
			return;
		}
		if (suffix!=null && !f.getName().endsWith(suffix)) {
			return;
		}
		System.out.println("Indexing file " + f.getCanonicalPath());
		
		Document doc = new Document();
		doc.add(new Field("contents", new FileReader(f)));		
		doc.add(new Field("filename", f.getCanonicalPath(), Field.Store.YES, Field.Index.ANALYZED));
		
		indexWriter.addDocument(doc);
	}

}
