package com.example.bmi;

import java.util.List;

public interface BMIIterator {
    boolean hasNext();
    String next();
}

public class IteratorBMIRecords {
    public static class RecordIterator implements BMIIterator {
        private List<String> records;
        private int position = 0;
        
        public RecordIterator(List<String> r) { records = r; }
        
        public boolean hasNext() {
            return position < records.size();
        }
        
        public String next() {
            return hasNext() ? records.get(position++) : null;
        }
    }
    
    public static class RecordCollection {
        private List<String> records;
        public RecordCollection(List<String> r) { records = r; }
        public BMIIterator getIterator() {
            return new RecordIterator(records);
        }
    }
}
