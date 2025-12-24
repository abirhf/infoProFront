export interface PieceDTO {
  id: number;
  reference: string;
  nom: string;
  description: string;
  modeleVehicule: string;
  garantie: string;
  dateLivraison: string;
  prix: number;
  image: string;
  categorieId?: number;
  categorieNom?: string;
  parentId?: number;
  parentNom?: string;
  enfants: PieceDTO[];
}

