package com.devteria.identity_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedResponse<T> {
    private List<T> items;        // Danh sách dữ liệu của trang hiện tại
    private int currentPage;      // Trang hiện tại (0-based)
    private long totalItems;      // Tổng số bản ghi
    private int totalPages;       // Tổng số trang
}
