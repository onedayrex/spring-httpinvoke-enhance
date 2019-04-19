package com.git.onedayrex.springhttpinvokeenhance.service;

import com.git.onedayrex.springhttpinvokeenhance.annotation.InvokeService;
import org.springframework.stereotype.Service;

@Service
@InvokeService(InvokeTestServic.class)
public class InvokeTestServiceImpl implements InvokeTestServic{
}
