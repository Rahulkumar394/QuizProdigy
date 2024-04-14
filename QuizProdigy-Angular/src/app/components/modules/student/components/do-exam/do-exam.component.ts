import { isPlatformBrowser } from '@angular/common';
import {
  Component,
  ElementRef,
  HostListener,
  Inject,
  OnInit,
  PLATFORM_ID,
  ViewChild,
} from '@angular/core';
import { Router } from '@angular/router';
import screenfull from 'screenfull';

@Component({
  selector: 'app-do-exam',
  templateUrl: './do-exam.component.html',
  styleUrl: './do-exam.component.css',
})
export class DoExamComponent implements OnInit {
  // Media Recording
  @ViewChild('videoElement')
  videoElement!: ElementRef;
  mediaRecorder!: MediaRecorder;
  recordedBlobs!: Blob[];

  //Declare variable
  remaningTime: any;
  n: any = 50;
  sno = Array(this.n).fill(0);

  constructor(
    private route: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    this.timer(30);
  }
  videoRef: any;
  ngOnInit(): void {
    // initializing video recording on startup
    this.startRecording();

    if (isPlatformBrowser(this.platformId)) {
      this.videoRef = document.getElementById('video');
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

  setUpCamera() {
    navigator.mediaDevices
      .getUserMedia({
        video: { width: 360, height: 250 },
        audio: false,
      })
      .then((stram) => {
        console.log(stram);
        this.videoRef.srcObject = stram;
      });
  }

  timer(minute: any) {
    // let minute = 1;
    let seconds: number = minute * 60;
    let textSec: any = '0';
    let statSec: number = 60;

    const prefix = minute < 10 ? '0' : '';

    const timer = setInterval(() => {
      seconds--;
      if (statSec != 0) statSec--;
      else statSec = 59;

      if (statSec < 10) {
        textSec = '0' + statSec;
      } else textSec = statSec;

      this.remaningTime = `${prefix}${Math.floor(seconds / 60)}:${textSec}`;

      if (seconds == 0) {
        console.log('finished');
        clearInterval(timer);
      }
    }, 1000);
  }

  // Start Recording method
  startRecording() {
    // Check if mediaRecorder is initialized and already recording
    if (this.mediaRecorder && this.mediaRecorder.state === 'recording') {
      console.warn('Already recording.');
      return;
    }

    // Check if mediaRecorder is initialized
    if (!this.mediaRecorder) {
      console.error('MediaRecorder not initialized.');
      return;
    }

    // Start recording
    this.mediaRecorder.start();
    console.log('Recording started.');
  }

  // After initialization
  ngAfterViewInit() {
    // Initialize the video element
    const video: HTMLVideoElement = this.videoElement.nativeElement;
    navigator.mediaDevices
      .getUserMedia({ video: true, audio: false })
      .then((stream) => {
        video.srcObject = stream;
        this.mediaRecorder = new MediaRecorder(stream);
        this.recordedBlobs = [];
        this.mediaRecorder.ondataavailable = (event) => {
          if (event.data && event.data.size > 0) {
            this.recordedBlobs.push(event.data);
          }
        };
        this.mediaRecorder.start();
      })
      .catch((error) => {
        console.error('Error accessing media devices:', error);
      });
  }

  @HostListener('window:beforeunload')
  async ngOnDestroy() {
    // Stop recording and save the video on exit
    await this.stopRecording();
    await this.saveVideo();
  }

  async stopRecording() {
    return new Promise<void>((resolve, reject) => {
      this.mediaRecorder.onstop = () => {
        resolve();
      };
      this.mediaRecorder.stop();
    });
  }

  async saveVideo() {
    const blob = new Blob(this.recordedBlobs, { type: 'video/webm' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    document.body.appendChild(a);
    a.style.display = 'none';
    a.href = url;
    a.download = 'recorded-video.webm';
    a.click();
    window.URL.revokeObjectURL(url);
  }
}
