package com.book.store.common.dto;

import com.book.store.common.exceptions.CustomException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;

public final class PaginableResultSet<T, R> {

	private final int pageSize;

	private final int pageNumber;

	private final int totalPages;

	private final int totalItems;

	private final List<R> content;

	/**
	 * @param page : page of entity
	 * @param mapper : method used to convert entity to dto , passed as lambda expression
	 * (hoc)
	 * @throws CustomException : if page null this function throws custom exception
	 */
	private PaginableResultSet(Page<T> page, Function<T, R> mapper) {
		if (Objects.isNull(page))
			throw new CustomException("page cannot be null");
		this.pageSize = page.getPageable().getPageSize();
		this.pageNumber = page.getPageable().getPageNumber();
		this.totalPages = page.getTotalPages();
		this.totalItems = Long.valueOf(page.getTotalElements()).intValue();
		this.content = CollectionUtils.isEmpty(page.getContent()) ? Collections.emptyList()
				: page.getContent().stream().map(mapper::apply).collect(Collectors.toList());
	}

	private PaginableResultSet(int pageSize, int pageNumber, int totalPages, int totalItems, List<R> content) {
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.totalPages = totalPages;
		this.totalItems = totalItems;
		this.content = content;
	}

	/**
	 * @param pageSize
	 * @param pageNumber
	 * @param totalPages
	 * @param totalItems
	 * @param content
	 * @param <T> : source entity
	 * @param <R> : source dto 
	 * @return
	 */
	public static <T, R> PaginableResultSet<T, R> of(int pageSize, int pageNumber, int totalPages,
			int totalItems, List<R> content) {
		return new PaginableResultSet<>(pageSize, pageNumber, totalPages, totalItems, content);
	}

	public static <T, R> PaginableResultSet<T, R> of(Page<T> page, Function<T, R> mapper) {
		return new PaginableResultSet<>(page, mapper);
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public List<R> getContent() {
		return content;
	}

}