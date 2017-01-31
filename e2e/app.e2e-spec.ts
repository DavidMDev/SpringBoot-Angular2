import { NouveauDossierPage } from './app.po';

describe('nouveau-dossier App', function() {
  let page: NouveauDossierPage;

  beforeEach(() => {
    page = new NouveauDossierPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
