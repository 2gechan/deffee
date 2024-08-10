package com.gechan.openmarket.dto.page;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<T> {

    private List<T> dtoList; // 화면에 보여질 dto list

    private List<Integer> pageNumList;

    private PageRequestDTO pageRequestDTO;

    private boolean prev, next; // 이전, 다음 버튼 유무 -> 1 페이지에 위치 중이면 prev 버튼 비 활성화

    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "pageRes")
    public PageResponseDTO(List<T> dtoList, PageRequestDTO pageRequestDTO, int totalCount) {
        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = totalCount;

        // 현재 페이지가 7 이라면 7 / 10.0 = 0.7이고 Math.ceil(0.7) = 1, 1 * 10 = 10
        // 1 ~ 10 페이지 그룹에 있다는 의미
        int end = (int) Math.ceil(pageRequestDTO.getPage() / 10.0) * 10;

        // 1 ~ 10 그룹은 시작번호 1, 2 ~ 10 글부은 11 ~ 20 ...
        int start = end - 9;

        // 테이블 전체 데이터 개수 / 페이지 사이즈(default : 10)
        // 101 / 10.0 = 10.1 => Math.ceil(10.1) = 11
        // 실제 페이지 마지막 번호
        int last = (int) Math.ceil(totalCount / (double) pageRequestDTO.getSize());

        end = end > last ? last : end;

        // 11 ~ 20 그룹일 때 1 ~ 10 그룹으로 이동할 수 있는지
        this.prev = start > 1;

        // 1 ~ 10 그룹일 때 10 * 10일 때 101번 이상 게시물이 있는지
        // 한 페이지 당 10개의 게시물이 있고 1 ~ 10 페이지가 한 그룹이므로
        // 다음 그룹으로 넘어가기 위해선 101개 이상 데이터가 있어야 한다.
        this.next = totalCount > end * pageRequestDTO.getSize();

        // 페이지 그룹 리스트에 저장
        this.pageNumList = IntStream.rangeClosed(start, end)
                .boxed().collect(Collectors.toList());

        this.prevPage = prev ? start - 1 : 0;

        this.nextPage = next ? end + 1 : 0;

        this.current = pageRequestDTO.getPage();
    }
}
