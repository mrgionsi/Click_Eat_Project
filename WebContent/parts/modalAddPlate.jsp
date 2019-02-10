<!-- Modal -->
  <div class="modal-dialog modal-dialog-centered" id="ModalAddPlate"role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="ModalAddPlateTitle">Aggiunti nuovo utente</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form>
       		<div class="form-group">
       			<label for="nomePiatto">Nome piatto</label>
    			<input type="text" class="form-control" id="nomePiatto" placeholder="Inserisci il nome del piatto">
    			<small id="nomePiattoHelpBlock" class="form-text text-muted">
    				Il piatto è una parola lunga minimo 3 caratteri e massimo 35.
    			</small>
    			
    			<label for="categoriaPiatto">Categoria piatto</label>
    			<input type="text" class="form-control" id="categoriaPiatto" placeholder="Inserisci la categoria del piatto">
    			<small id="categoriaPiattoHelpBlock" class="form-text text-muted">
    				La categoria del piatto è una parola lunga minimo 5 caratteri e massimo 10.
    			</small>
    			
    			<label for="listaIngredienti">Lista Ingredienti</label>
    			<input type="text" class="form-control" id="listaIngredienti" placeholder="Inserisci la lista degli ingredienti">
    			<label for="listaIngredienti">Lista Ingredienti</label>
    			<small id="listaIngredientiHelpBlock" class="form-text text-muted">
    				Inserisci gli ingredienti separati da una virgola. Es: "Alici, Capperi, Sale, Olio".
    			</small>
    			
    			<label for="prezzoPiatto">Prezzo piatto</label>
    			<input type="text" class="form-control" id="prezzoPiatto" placeholder="Inserisci il prezzo">
    			<small id="prezzoPiattoHelpBlock" class="form-text text-muted">
    				Il prezzo del piatto è una numero con massimo cinque numeri a sinistra della virgola e sempre e solo due numeri a destra della virgola.
    			</small>	
       		</div>
       </form>
      </div>
      <div class="modal-footer">

        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>

        <button type="button" class="btn btn-primary btn-modal" id="btn-createplate" data-type="btn-createplate">Aggiungi</button>

      </div>
    </div>
  </div>
  
