import { Component } from '@angular/core';
import * as XLSX from 'xlsx';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss'],
  standalone: true,
  imports: []
})
export class UploadComponent {
  constructor(private http: HttpClient) {}

  onFileDropped(event: DragEvent): void {
    const file = event.dataTransfer?.files[0];
    if (file) this.readExcel(file);
  }

  readExcel(file: File): void {
    const reader = new FileReader();
    reader.onload = (e: any) => {
      const data = new Uint8Array(e.target.result);
      const workbook = XLSX.read(data, { type: 'array' });
      const sheet = workbook.Sheets[workbook.SheetNames[0]];
      const json = XLSX.utils.sheet_to_json(sheet);

      json.forEach((row: any) => {
        const payload = {
          reference: row.product_id,
          nom: row.product_name,
          categorieNom: row.category,
          parentId: row.parent_product_id || null
        };
        this.http.post('http://localhost:8082/api/pieces/import', payload).subscribe();
      });
    };
    reader.readAsArrayBuffer(file);
  }
onDragOver(event: DragEvent): void {
  event.preventDefault();
}

}
