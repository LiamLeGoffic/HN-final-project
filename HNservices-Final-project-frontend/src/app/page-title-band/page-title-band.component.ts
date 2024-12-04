import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-page-title-band',
  standalone: true,
  imports: [],
  templateUrl: './page-title-band.component.html',
  styleUrl: './page-title-band.component.css'
})
export class PageTitleBandComponent {

  @Input() title: string = '';
}
