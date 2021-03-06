import http from "./httpService";
import { apiUrl } from "../config.json";

const apiEndpoint = apiUrl + "/stock/";

function stockUrl(id) {
  return `${apiEndpoint}${id}`;
}

export function getStocks() {
  return http.get(apiEndpoint);
}

export function getStock(stockId) {
  return http.get(stockUrl(stockId));
}

export function saveStock(stock) {

    //return http.put(stockUrl(stock.id), body);
    return http.post(apiEndpoint, stock);
  
}

export function deleteStock(stockId) {
  console.log(stockId);
  console.log(stockUrl(stockId))
  http.delete(stockUrl(stockId));
}

export function addSupplierToStock(stockSupplier) {

    //return http.put(stockUrl(stock.id), body);
    return http.post(apiEndpoint+'addSupplier', stockSupplier);
  
}

export function addDrugToStock(stockDrug) {
    //return http.put(stockUrl(stock.id), body);
    return http.post(apiEndpoint+'addDrug', stockDrug);
  
}
