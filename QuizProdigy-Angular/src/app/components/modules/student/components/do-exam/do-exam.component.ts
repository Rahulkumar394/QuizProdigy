import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import screenfull from 'screenfull';
import { isPlatformBrowser } from '@angular/common';
import { Inject, PLATFORM_ID } from '@angular/core';

@Component({
  selector: 'app-do-exam',
  templateUrl: './do-exam.component.html',
  styleUrl: './do-exam.component.css',
})
export class DoExamComponent implements OnInit {

//Declare variable
remaningTime:any;
n:any=50;
sno = Array(this.n).fill(0);

  constructor(private route: Router,@Inject(PLATFORM_ID) private platformId: Object) {
    this.timer(30);
  }
  videoRef:any;
  ngOnInit(): void {

    if (isPlatformBrowser(this.platformId)) {
    
      this.videoRef=document.getElementById('video');
      console.log(this.videoRef);
      this.setUpCamera();
    }


    if (screenfull.isEnabled) {
      screenfull.toggle();
    }
    if (screenfull.isEnabled) {
      screenfull.on('change', () => {
        if (screenfull.isFullscreen) {
          console.log('Entered full screen mode');
          // Update UI or perform actions when entering full screen
        } else {
          console.log('Exited full screen mode');
          this.route.navigateByUrl('/student');
          // Update UI or perform actions when exiting full screen
        }
      });
    }
  }

  setUpCamera()
  {
    navigator.mediaDevices.getUserMedia({
      video: {width:360,height:250},
      audio: false
    }).then((stram=>{
      console.log(stram);
      this.videoRef.srcObject=stram;
    }));
  }

  timer(minute:any) {
    // let minute = 1;
    let seconds: number = minute * 60;
    let textSec: any = "0";
    let statSec: number = 60;

    const prefix = minute < 10 ? "0" : "";

    const timer = setInterval(() => {
      seconds--;
      if (statSec != 0) statSec--;
      else statSec = 59;

      if (statSec < 10) {
        textSec = "0" + statSec;
      } else textSec = statSec;

      this.remaningTime = `${prefix}${Math.floor(seconds / 60)}:${textSec}`;

      if (seconds == 0) {
        console.log("finished");
        clearInterval(timer);
      }
    }, 1000);
  }
}







  



