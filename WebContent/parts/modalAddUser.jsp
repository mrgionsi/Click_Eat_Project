<!-- Modal -->
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="ModalAddtableTitle">Aggiunti nuovo utente</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form>
       		<div class="form-group">
       			<label for="nomeUtente">Nome utente</label>
    			<input type="text" class="form-control" id="nomeUtente" placeholder="Inserisci il nome">
    			<label for="cognomeUtente">Cognome utente</label>
    			<input type="text" class="form-control" id="cognomeUtente" placeholder="Inserisci il cognome">
    			<label for="ruoloUtente">Ruolo utente</label>
    			<input type="text" class="form-control" id="ruoloUtente" placeholder="Inserisci il ruolo">
    			<label for="idLogin">Username utente</label>
    			<input type="text" class="form-control" id="idLogin" placeholder="Inserisci lo username">
    			<label for="passwordUtente">Password utente</label>
    			<input type="text" class="form-control" id="passwordUtente" placeholder="Inserisci la password">	
       		</div>
       </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

        <button type="button" class="btn btn-primary">Aggiungi</button>

      </div>
    </div>
  </div>
  
  <script src="./contents/scripts/modalFormUser.js"></script>