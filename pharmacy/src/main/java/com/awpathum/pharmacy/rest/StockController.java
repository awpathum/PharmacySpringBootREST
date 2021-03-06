package com.awpathum.pharmacy.rest;

import java.util.List;

import com.awpathum.pharmacy.classes.DrugStock;
import com.awpathum.pharmacy.classes.StockResponse;
import com.awpathum.pharmacy.classes.SupplierStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.awpathum.pharmacy.entity.Drug;
import com.awpathum.pharmacy.entity.Stock;
import com.awpathum.pharmacy.entity.Supplier;
import com.awpathum.pharmacy.service.DrugService;
import com.awpathum.pharmacy.service.StockService;
import com.awpathum.pharmacy.service.SupplierService;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins="http://localhost:3000")
public class StockController {


	// need to inject the stock service
	@Autowired
	private StockService stockService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private DrugService drugService;

	@PostMapping("/")
	public Stock addStock(@RequestBody Stock theStock) {
//		String id = theStock.getId();
//		theStock.setId(id);
		System.out.println("theStock");
		System.out.println(theStock.getSupplier());
		System.out.println(theStock.getSupplier());

		stockService.saveStock(theStock);

		return theStock;
	}

	@GetMapping("/")
	public List<StockResponse> listStocks() {

		// get stocks form the service
		List<StockResponse> theStocks = stockService.getStocks();
		System.out.println(theStocks);
		return theStocks;
	}
	//get stock by id
	@GetMapping("/{stockId}")
	public StockResponse getStock(@PathVariable String stockId){
		return stockService.getStockResponse(stockId);
	}

	@PutMapping("/")
	public Stock updateStock(@RequestBody Stock theStock) {

		stockService.saveStock(theStock);

		return theStock;
	}

	@DeleteMapping("/{theId}")
	public String delete(@PathVariable String theId) {

		//reduce drug quantity from general stock
		Drug theDrugFromStock = stockService.getStock(theId).getDrug();
		Integer toDeleteQuantity = stockService.getStock(theId).getQuantity();
		System.out.println(toDeleteQuantity);
		String toDeleteDrugId = theDrugFromStock.getId();


		Drug genDrug = drugService.getDrug(toDeleteDrugId);
		Integer remQuantity = genDrug.getQuantity() - toDeleteQuantity;

		genDrug.setQuantity(remQuantity);

		drugService.saveDrug(genDrug);

		// delete the stock
		stockService.deleteStock(theId);

		return "Deleted" + theId;

	}

	@PostMapping("/addSupplier")
	public String addSupplier(@RequestBody SupplierStock supplierStock) {

		String supplierId = supplierStock.getSupplierId();
		String stockId = supplierStock.getStockId();

		Supplier supplier = supplierService.getSupplier(supplierId);


		Stock stock = stockService.getStock(stockId);

		supplier.add(stock);
		stock.setSupplier(supplier);
		supplierService.saveSupplier(supplier);
		stockService.saveStock(stock);
		//System.out.println("Done!");
		return "Done";
	}


	@PostMapping("/addDrug")
	public String addDrug(@RequestBody DrugStock drugStock) {

		String drugId = drugStock.getDrugId();
		String stockId = drugStock.getStockId();

		Drug drug = drugService.getDrug(drugId);


		Stock stock = stockService.getStock(stockId);

		drug.add(stock);
		stock.setDrug(drug);
		drugService.saveDrug(drug);
		stockService.saveStock(stock);

		//

		//String drugId = theStock.getId().substring(0,5);
		//System.out.println(drugId);
		Drug theDrug = drugService.getDrug(drugId);
		Integer curQuantity = theDrug.getQuantity();

		Integer newQuantity = stock.getQuantity();
		Integer updatedQuantity = curQuantity + newQuantity;


		theDrug.setQuantity(updatedQuantity);

		drugService.saveDrug(theDrug);
		//
		//System.out.println("Done!");
		return "Done";
	}

}
