package list;
public interface SimpleList {
    boolean isEmpty();              // 리스트가 비었는가?
    void add(int data);             // 맨 뒤에 데이터 삽입
    int remove();                   // 첫번째 데이터 삭제
    int removeAt(int index);        // index번째 데이터 삭제
    boolean remove(int data);       // 특정 데이터 삭제
    boolean contains(int data);     // 특정 데이터가 존재하는가?
    int  size();                    // 리스트의 크기
    SimpleListIterator iterator();  // iterator 반환
}
