import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NotificationsService } from 'angular2-notifications';
import { AlertService } from '../service/alert.service';
import { DiscountService } from '../service/discount.service';

@Component({
  selector: 'app-discount',
  templateUrl: './discount.component.html',
  styleUrls: ['./discount.component.css'],
})
export class DiscountComponent implements OnInit {
  coupons: any;
  name: any;
  discount: any;
  exp: any;
  id: any;
  constructor(
    private discountService: DiscountService,
    private format: DatePipe,
    private alertService: AlertService
  ) {}
  dataInput: any;
  ngOnInit(): void {
    this.loadAllCoupon();
  }
  loadAllCoupon() {
    this.discountService.findAll().subscribe(
      (value) => {
        this.coupons = value;
        console.log(value);
      },
      (error) => {
        console.log(error);
      }
    );
  }
  save(data: any, type: any) {
    switch (type) {
      case 'New': {
        this.discountService
          .insert({
            name: data.name.toUpperCase(),
            discount: data.discount,
            exp: this.format.transform(data.exp, 'yyyy-MM-dd HH:mm:ss'),
          })
          .subscribe(
            (value) => {
              alert('Successfully Added!');
              this.loadAllCoupon();
            },
            (error) => {
              alert('Failed Added!');
            }
          );
        break;
      }
      case 'Update': {
        this.discountService
          .update({
            id: this.id,
            name: data.name.toUpperCase(),
            discount: data.discount,
            exp: this.format.transform(data.exp, 'yyyy-MM-dd HH:mm:ss'),
          })
          .subscribe(
            (data) => {
              alert('Successfully updated!');
              this.loadAllCoupon();
            },
            (error) => {
              alert('Failed updated!');
            }
          );
        break;
      }
    }
  }
  delete(id: string) {
    this.discountService.delete(id).subscribe(
      (value) => {
        alert('Successfully Deleted!');
        this.loadAllCoupon();
      },
      (error) => {
        alert('Failed Deleted!');
      }
    );
  }
  type: string | undefined;
  showData(id: string, type: string) {
    this.type = type;
    switch (type) {
      case 'New': {
        this.name = '';
        this.discount = '';
        this.exp = '';
        break;
      }
      case 'Update': {
        this.discountService.findOne(id).subscribe(
          (data) => {
            this.name = data.name;
            this.discount = data.discount;
            this.exp = data.exp;
            this.id = data.id;
          },
          (error) => {
            console.log(error);
          }
        );
        break;
      }
    }
  }
  clear() {
    this.name = '';
    this.discount = '';
    this.exp = '';
  }
}
