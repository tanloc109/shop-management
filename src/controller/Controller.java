/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface Controller {
       
    <T> List<T> sortByName(List<T> list);
    
    <T> List<T> sortByQuantity(List<T> list);
    
    <T> List<T> searchByName(List<T> list, String key);
}
