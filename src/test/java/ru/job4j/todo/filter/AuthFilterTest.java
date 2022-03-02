package ru.job4j.todo.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

public class AuthFilterTest {

    @Test
    public void whenURIEqualsAuth() throws ServletException, IOException {
        AuthFilter authFilter = new AuthFilter();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain filterChain = Mockito.mock(FilterChain.class);
        Mockito.when(request.getRequestURI()).thenReturn("auth.do");
        authFilter.doFilter(request, response, filterChain);
        Mockito.verify(filterChain).doFilter(request, response);
    }

    @Test
    public void whenURIEqualsReg() throws ServletException, IOException {
        AuthFilter authFilter = new AuthFilter();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain filterChain = Mockito.mock(FilterChain.class);
        Mockito.when(request.getRequestURI()).thenReturn("reg.do");
        authFilter.doFilter(request, response, filterChain);
        Mockito.verify(filterChain).doFilter(request, response);
    }

}