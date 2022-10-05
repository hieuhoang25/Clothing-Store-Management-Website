import { Injectable } from '@angular/core';
import { NotificationsService } from 'angular2-notifications';

@Injectable({
    providedIn: 'root'
  })
export class AlertService {

constructor(private service:NotificationsService) { }
onSuccess(message:string) {
    this.service.success("Sucesss", message, {
      position:['top','right'],
      timeOut:1000,
      animated:'fade',
      showProgressBar:false
    })
  }
  onError(message:string) {
    this.service.error("Error", message, {
      position:['top','right'],
      timeOut:1000,
      animated:'fade',
      showProgressBar:false
    })
  }
}
