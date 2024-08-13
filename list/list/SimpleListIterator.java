package list;
public interface SimpleListIterator {
    boolean hasNext();      // 다음요소가 존재하는가?
    int next();             // 현재 요소 출력 후 인덱스++
    boolean hasPrevious();  // 이전 요소가 존재하는가?
    int previous();         // 현재 요소 출력 후 인덱스--
    void remove();          // 현재 요소 삭제
}